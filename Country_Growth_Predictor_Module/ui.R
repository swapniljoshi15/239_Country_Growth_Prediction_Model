library(shiny)
require(mplot)

fig.width <- 600
fig.height <- 450

shinyUI(pageWithSidebar(
  headerPanel("Predictive Model for Nation Development with Infrastructure Data"),
  
  sidebarPanel(
    div(
      uiOutput("years")
    ),
    selectInput("model",
                strong("Linear model to evaluate"),
                choices=c("","Multiple Regression",
                          "Random Forests")),
    selectInput("dependent", "Dependent Variable:", c("","gdp_per_capita")),
    uiOutput("independent")
  ),
  
  mainPanel(
    div(tableOutput("regTab")),
    textOutput("rmseTestText"),
    textOutput("rmseTrainText"),
    conditionalPanel(condition="input.model== 'Multiple Regression' ",
       textOutput("modelRSquaredValue")  
    ),
    conditionalPanel(condition="input.model== 'Multiple Regression' ",
       div(plotOutput("termPlot"))  
    ),
    conditionalPanel(condition="(input.model== 'Multiple Regression' || input.model== 'Random Forests') && input.independent != ''",
      div(plotOutput("regPlot")) 
    ),
    conditionalPanel(condition="(input.model== 'Multiple Regression' || input.model== 'Random Forests') && input.independent != ''",
      div("Model Information:"),
      div(class="span7", verbatimTextOutput("regSummary")) 
    )
    #plotOutput("scatterplot3D")),
  
)))