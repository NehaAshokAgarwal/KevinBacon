# KevinBacon
It implements the famous graph problem of finding the Kevin numbers of the actors by efficiently scanning the file dataset provided.  
This project is related to my Advanced Algorithm course at UMB. 
Here there are two short exercises - Grid.java and DijkstrTieSP.java. 
DijkstrTieSP.java is basically a little modifies version of the Dijkstra algorithm in which when the weights of both the route is same, we go by number of edges. If teh number of edges in the other route is less than the orignal one, we relax the edge. Unlike the Dijsktra, where is only relaxes the edge when the weight of the new route is smaller than that of the orignal route. 
DegreesOfSEparation.java id the program which find the Kevin Bacon number by building the graph from the file data set provoded by using the SymbolGraph, Graph, BreathFirstPath as teh underlying data structires from the algs4.jar file. 
This project is taken from the Princeton Creative assignment website. 
