# Algoritmo de Kruskal utilizando conjuntos ajenos y una versión modificada de unión por rango

Implementación para obtener el máximo ancho de banda total que puede haber en una red telefónica de n estaciones y k canales.

Implementación en Java del algoritmo de Kruskal con unión por rango y conjuntos ajenos, generando un árbol de peso máximo en lugar de peso mínimo. La información es leída de un archivo XML con la siguiente estructura

```xml
<Sucursales>
  <Sucursal>Distrito Federal</Sucursal>
  <Sucursal>Queretaro</Sucursal>
  <Sucursal>Hidalgo</Sucursal>
  ...
  <Canales>
    <Canal>
      <Sucursal_a>Distrito Federal</Sucursal_a>
      <Sucursal_b>Puebla</Sucursal_b>
      <Ancho_Banda>28</Ancho_Banda>
    </Canal>
    <Canal>
      <Sucursal_a>Distrito Federal</Sucursal_a>
      <Sucursal_b>Nuevo Leon</Sucursal_b>
      <Ancho_Banda>23</Ancho_Banda>
    </Canal>
    ...
  </Canales>
</Sucursales>
```

Si se tiene instalado graphviz y se ejecuta sobre un sistema unix se puede graficar las dos redes (inicial y después de aplicar Kruskal), crea dos archivos terminación .DOT para poder hacer las gráficas. 
