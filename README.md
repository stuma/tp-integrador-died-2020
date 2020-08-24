Modo de Uso:
    
    1) Ejecutar InicializacionEstadoPedido. Crea las entidades EstadoPedido. Verificar en BD que los ID sean: 1,2,3,4 o el programa fallará
    2) Ejecutar View/gui/app/App.java. 
    
    Consideraciones:
    
        -Tanto TestGrafo como TestPedido tienen una llamada a un método que permite crear un grafo. Dicha linea está comentada. Si se ejecuta esta clase con 
        dicha linea descomentada, se creará en la BD el grafo. Pero, para volver a ejecutar dicho TestGrafo, debe comentarse de nuevo o 
        arrojará error. 
        
        -La aplicación maneja un solo grafo en todo el tiempo. Es decir, al agregar una planta, se agrega a un grafo ya existente, o en caso de no 
        existir, se crea uno desde cero. Pero siempre es un grafo. 
        
        
        
         
        

    