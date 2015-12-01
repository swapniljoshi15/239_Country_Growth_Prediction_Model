library(shiny)

shinyUI(pageWithSidebar(
  
  headerPanel("Pearson Corrrelation"),
  
  sidebarPanel(
    div(
      uiOutput("years")
    ),
    div(
      selectInput("dependent", "Choose a dependent variable:", 
                  choices = c("","elec_con","boradband_sub","internet_usr","gdp_per_capita","import","export"))
      
    ),
    div(
      selectInput("independent", "Choose a independent variable:", 
                  choices = c("","elec_con","boradband_sub","internet_usr","gdp_per_capita","import","export"))
      
    )
  ),
  
  mainPanel(
    textOutput("pcoeff"),
    br(),
    textOutput("scoeff"),
    br(),
    textOutput("kcoeff"),
    br(),
    textOutput("abcd"),
    plotOutput("dataPlot"),
    #plotOutput("pcoeffnumplot"),
    plotOutput("pcoeffplot"),
    #plotOutput("scoeffnumplot"),
    plotOutput("scoeffplot"),
    #plotOutput("kcoeffnumplot"),
    plotOutput("kcoeffplot"),
    div(
      plotOutput("termPlot")
    )
    
  )
))
