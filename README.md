Falta:

    DAO Camión:

        -public Optional<Camion> getCamionPatente(String patente)

            Retorna camion que tenga la patente dada como argumento. Sino, retorna un optional vacío
            (Se usa en la validación de altaCamion para evitar agregar camiones con una misma patente)

       - public List<Camion> getListaCamionesAtributos (Camion camion):

            Busqueda de camión por filtros-> si todos los parámetros son nulos, debe retornar lo que retorna
            getAll.

    DAO Orden Pedido:

       - public List<OrdenPedido> buscarOrdenPorEstado (String descripcion)
            Retorna todas las ordenes de pedido que tengan como EstadoPedido la descripción dada como parámetro


