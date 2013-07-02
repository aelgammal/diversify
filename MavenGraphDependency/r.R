plotDependency <- function(data) {
 # tmp <- data[order(data$version),]
  yMax = max(max(data$directDependency), max(data$allDependency), max(data$allUsed), max(data$directUsed))
  plot(data$directDependency, type="l", ylim = c(0,yMax), xlab="version", ylab="",lwd=2)
  lines(data$allDependency, col="red",lwd=2)
  lines(data$allUsed, col="blue",lwd=2)
  lines(data$directUsed, col="green",lwd=2)
}

barPlotDependecy  <- function(data, l) {
  d <- data[c(2:length(data$version)),]
  plot(d$directDependencyNew + d$directDependencySame, type="h", lwd=l,
       col="red", ylab="dependency", xlab="version",
       ylim=c(0,max(d$directDependencyNew + d$directDependencySame)))
  points(d$directDependencySame, type="h", lwd=l, col="darkblue") 
}