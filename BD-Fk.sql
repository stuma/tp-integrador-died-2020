ALTER TABLE camino ADD CONSTRAINT planta_id FOREIGN KEY (planta_id) REFERENCES planta(id);
ALTER TABLE camino ADD CONSTRAINT fk_ordenPedido_id FOREIGN KEY (ordenPedido_id) REFERENCES ordenpedido(id);

ALTER TABLE ordenpedido ADD CONSTRAINT destino_id FOREIGN KEY (destino_id) REFERENCES planta(id);
ALTER TABLE ordenpedido ADD CONSTRAINT origen_id FOREIGN KEY (origen_id) REFERENCES planta(id);
ALTER TABLE ordenpedido ADD CONSTRAINT estadoPedido_id FOREIGN KEY (estadoPedido_id) REFERENCES estadopedido(id);
ALTER TABLE ordenpedido ADD CONSTRAINT camion_id FOREIGN KEY (camion_id) REFERENCES camion(id);

ALTER TABLE planta ADD CONSTRAINT grafo_id FOREIGN KEY (grafo_id) REFERENCES grafo(id);

ALTER TABLE ruta ADD CONSTRAINT fk_grafo_id FOREIGN KEY (grafo_id) REFERENCES grafo(id);
ALTER TABLE ruta ADD CONSTRAINT plantaDestino_id FOREIGN KEY (plantaDestino_id) REFERENCES planta(id);
ALTER TABLE ruta ADD CONSTRAINT plantaOrigen_id FOREIGN KEY (plantaOrigen_id) REFERENCES planta(id);


ALTER TABLE item ADD CONSTRAINT ordenPedido_id FOREIGN KEY (ordenPedido_id) REFERENCES ordenpedido(id);
ALTER TABLE stock ADD CONSTRAINT fk_planta_id FOREIGN KEY (planta_id) REFERENCES planta(id);

ALTER TABLE stock ADD CONSTRAINT fk_insumo_id FOREIGN KEY (insumo_id) REFERENCES insumo(id);
ALTER TABLE item ADD CONSTRAINT fkinsumo_id FOREIGN KEY (insumo_id) REFERENCES insumo(id);
ALTER TABLE ordenpedido ADD CONSTRAINT fk_planta FOREIGN KEY (planta_id) REFERENCES planta(id);
