library(shiny)

dataPath <- "C:\\Users\\Swapnil\\Desktop\\sjsu\\239\\project\\dataforregression\\yearsdata\\yearwisedata\\"

shinyServer(function(input, output, session) {
  
  output$years <- renderUI({
    checkboxGroupInput("years", "Years:",c("scaled_normalized_1980_2012","2006-2012","2000-2005","1980-1999","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012"))
  })
  
  calculateCoeff <- reactive({
    for (yearInput in input$years) {
      
      # if the merged dataset does exist, append to it
      if (exists("countrygrowth_data")){
        temp_countrygrowth_data <- read.csv(file=paste(dataPath,yearInput,".csv",sep=''), header=TRUE, sep=",")
        countrygrowth_data<-rbind(countrygrowth_data, temp_countrygrowth_data)
        rm(temp_countrygrowth_data)
      }
      
      # if the merged dataset doesn't exist, create it
      if (!exists("countrygrowth_data")){
        countrygrowth_data <- read.csv(file=paste(dataPath,yearInput,".csv",sep=''), header=TRUE, sep=",")
      }
      
      indvar <- input$independent
      depvar <- input$dependent
      
      pcoeff = cor(countrygrowth_data[indvar], countrygrowth_data[depvar], use="all.obs", method="pearson")
      scoeff = cor(countrygrowth_data[indvar], countrygrowth_data[depvar], use="all.obs", method="spearman")
      kcoeff = cor(countrygrowth_data[indvar], countrygrowth_data[depvar], use="all.obs", method="kendall")
      
      line1 <- paste("pearson Coefficient:", pcoeff)
      line2 <- paste("spearman Coefficient:", scoeff)
      line3 <- paste("kendall Coefficient:", kcoeff)
      output$pcoeff <- renderText({paste(line1)})
      output$scoeff <- renderText({paste(line2)})
      output$kcoeff <- renderText({paste(line3)})
      
      plotData <- countrygrowth_data[,c(indvar,depvar)]
      attach(plotData)
      
      output$dataPlot <- renderPlot(plot(plotData, xlab = indvar, ylab = depvar, main = "Data Points" ))
      
      par(mfrow=c(4,2))
      output$pcoeffnumplot <- renderPlot(corrplot(pcoeff,method = "number"))
      output$pcoeffplot <- renderPlot(plot(pcoeff, xlab = indvar, ylab = depvar, main = "Pearson Coefficient" ))
      output$scoeffnumplot <- renderPlot(corrplot(scoeff,method = "number"))
      output$scoeffplot <- renderPlot(plot(scoeff,col = "dark red",xlab = indvar, ylab = depvar, main = "Spearman Coefficient" ))
      output$kcoeffnumplot <- renderPlot(corrplot(kcoeff,method = "number"))
      output$kcoeffplot <- renderPlot(plot(kcoeff,col = "dark red",xlab = indvar, ylab = depvar, main = "Kendall Coefficient" ))
      
    }
    
  })
  
  output$abcd <- renderText({ 
    
    if(!is.null(input$independent) && !is.null(input$years) && !is.null(input$dependent)
       && input$independent != '' && input$dependent != ''){
      calculateCoeff()
    }
    
  })
  
  
})