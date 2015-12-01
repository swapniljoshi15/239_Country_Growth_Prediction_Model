library(shiny)
library(scatterplot3d)
require(ggplot2)
require(mplot)
require(scatterplot3d)
require(randomForest)

#countrygrowth.data <- read.csv("C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\dataforregression\\yearsdata\\2007.csv")
dataPath <- "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\dataforregression\\yearsdata\\yearwisedata\\"

shinyServer(function(input, output, session) {
  
  output$years <- renderUI({
    checkboxGroupInput("years", "Years:",c("scaled_normalized_1980_2012","2006-2012","2000-2005","1980-1999","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012"))
  })
  
  output$independent <- renderUI({
    checkboxGroupInput("independent", "Independent Variables:",c("elec_con","boradband_sub","internet_usr","import","export"))
  })
  
  runRegression <- reactive({
    
    for (yearInput in input$years) {
      
      # if the merged dataset does exist, append to it
      if (exists("countrygrowth.data") && !is.null(countrygrowth.data)){
        temp_countrygrowth_data <- read.csv(file=paste(dataPath,yearInput,".csv",sep=''), header=TRUE, sep=",")
        countrygrowth.data<-rbind(countrygrowth.data, temp_countrygrowth_data)
        rm(temp_countrygrowth_data)
      }
      
      # if the merged dataset doesn't exist, create it
      if (!exists("countrygrowth.data")){
        countrygrowth.data <- read.csv(file=paste(dataPath,yearInput,".csv",sep=''), header=TRUE, sep=",")
      }
      
    }
    
    countrygrowth.train.indexes <- sample(1:nrow(countrygrowth.data), 0.75*nrow(countrygrowth.data), replace=F)
    countrygrowth.train <- countrygrowth.data[countrygrowth.train.indexes,]
    countrygrowth.test <- countrygrowth.data[- countrygrowth.train.indexes, ]
    
    if (input$model == "Multiple Regression") {
      result <- lm(as.formula(paste(input$dependent," ~ ",paste(input$independent,collapse="+"))),data=countrygrowth.data)
      rsquaredValue <- summary(result)$r.squared
    }
    else if (input$model == "Random Forests") {
      result <- randomForest(as.formula(paste(input$dependent," ~ ",paste(input$independent,collapse="+"))), data = countrygrowth.train, ntree = 1000)
      rsquaredValue <- ""  
    }
    
    countrygrowth.predTest <- predict(result, newdata = countrygrowth.test)
    
    actualTest <- countrygrowth.test$gdp_per_capita
    predictedTest <- countrygrowth.predTest
    errorTest <- actualTest - predictedTest
    rmseErrorTest <- rmse(errorTest)
    output$rmseTestText <- renderText({paste("RMSE for Test Data:", rmseErrorTest)})
    
    countrygrowth.predTrain <- predict(result, newdata = countrygrowth.train)
    actualTrain <- countrygrowth.train$gdp_per_capita
    predictedTrain <- countrygrowth.predTrain
    errorTrain <- actualTrain - predictedTrain
    rmseErrorTrain <- rmse(errorTrain)
    output$rmseTrainText <- renderText({paste("RMSE for Train Data:", rmseErrorTrain)})
    
    if(input$model == "Multiple Regression"){
      output$modelRSquaredValue <- renderText({paste("Model R-Squared Value:",rsquaredValue)}) 
    }
    result
  })
  
  output$regTab <- renderTable({
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != '' && input$years != ''){
      if(input$model == "Multiple Regression"){
        summary(runRegression())$coefficients    
      }
    } else {
      print(data.frame(Warning="Please select Model Parameters."))
    }
  })
  
  
  output$termPlot <- renderPlot({
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != '' && input$years != ''
       && input$model == "Multiple Regression"){
      resultsaaa <- runRegression()
      par(mfrow=c(2,3))
      termplot(resultsaaa,partial.resid = TRUE,col.res = "purple")
    } else {
      
    } 
  })
  
  output$regPlot <- renderPlot({
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != '' && input$years != ''){
      country_growth <- runRegression()
      par(mfrow=c(2,3))
      plot(country_growth)
      abline(country_growth)
    } else {
      
    } 
  })
  
  output$scatterplot3D <- renderPlot({
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != '' && input$years != ''
       && input$model == "Multiple Regression"){
      resultsaaa <- runRegression()
      scatterplot3d::scatterplot3d(resultsaaa$input$independent,resultsaaa$internet_usr,resultsaaa$boradband_sub)
    } else {
      
    } 
  })
  
  output$regSummary <- renderPrint({
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != '' && input$years != ''
       && (input$model == "Multiple Regression" || input$model == "Random Forests")){
      if (!is.null(summary(runRegression()))) {
        summary(runRegression())
      }
    } else {
      
    }
  })
  
  rmse <- function(error)
  {
    sqrt(mean(error^2))
  }
  
  # Function that returns Mean Absolute Error
  mae <- function(error)
  {
    mean(abs(error))
  }
  
  
})