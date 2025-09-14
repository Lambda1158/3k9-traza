package org.example;

import org.example.entidades.*;
import org.example.repository.inMemoryRepository;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");


        inMemoryRepository<Empresa> empresasRepository = new inMemoryRepository<>();



        Pais pais = new Pais();
        pais.setId(1l);
        pais.setNombre("Argentina");

        Provincia provinciaBSAS = new Provincia();
        provinciaBSAS.setId(1l);
        provinciaBSAS.setNombre("Buenos Aires");
        provinciaBSAS.setPais(pais);

        Localidad localidadCaba = new Localidad();
        localidadCaba.setId(1l);
        localidadCaba.setProvincia(provinciaBSAS);
        localidadCaba.setNombre("CABA");

        Domicilio domicilioCaba = Domicilio.builder().id(3l).Nombre("Domicilio de CABA").cp(1234).numbero(3211).localidad(localidadCaba).build();

        Localidad localidadLaPlata = new Localidad();
        localidadLaPlata.setId(2l);
        localidadLaPlata.setProvincia(provinciaBSAS);
        localidadLaPlata.setNombre("La Plata");

        Domicilio domicilioLaPlata = new Domicilio();
        domicilioLaPlata.setId(1l);
        domicilioLaPlata.setLocalidad(localidadLaPlata);
        domicilioLaPlata.setCp(5500);
        domicilioLaPlata.setNombre("Domicilio de la plata");
        domicilioLaPlata.setNumbero(33);


        Provincia provinciaCordoba = new Provincia();
        provinciaCordoba.setId(2l);
        provinciaCordoba.setNombre("Cordoba");
        provinciaCordoba.setPais(pais);

        Localidad localidadCarlosPaz = new Localidad();
        localidadCarlosPaz.setId(4l);
        localidadCarlosPaz.setProvincia(provinciaCordoba);
        localidadCarlosPaz.setNombre("Carlos Paz");

        Localidad localidadCC = Localidad.builder().id(3l).nombre("Cordoba Capital").provincia(provinciaCordoba).build();

        Domicilio domicilioCarlosPaz = Domicilio.builder().id(4l).Nombre("domicilio de carlos paz").cp(3320).numbero(32).localidad(localidadCarlosPaz).build();
        Domicilio domicilioCC = Domicilio.builder().id(2l).Nombre("domicilio de cordoba capital").cp(2230).numbero(22).localidad(localidadCC).build();

        Sucursal sucursalCaba = Sucursal.builder()
                .id(1l)
                .nombre("Sucursal numero 1")
                .horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(21,0))
                .domicilio(domicilioCaba)
                .build();

        Sucursal sucursalLaPlata = Sucursal.builder()
                .id(2l)
                .nombre("Sucursal numero 2")
                .horarioApertura(LocalTime.of(7,0))
                .horarioCierre(LocalTime.of(18,0))
                .domicilio(domicilioLaPlata)
                .build();

        Sucursal sucursalCordobaCapital = Sucursal.builder()
                .id(3l)
                .nombre("Sucursal numero 3")
                .horarioApertura(LocalTime.of(18,0))
                .horarioCierre(LocalTime.of(23,0))
                .domicilio(domicilioCC)
                .build();

        Sucursal sucursalVillaCarlosPaz = Sucursal.builder()
                .id(4l)
                .nombre("Sucursal numero 4")
                .horarioApertura(LocalTime.of(14,0))
                .horarioCierre(LocalTime.of(20,0))
                .domicilio(domicilioCarlosPaz)
                .build();

        Empresa EmpresaBuenosAires = Empresa.builder()
                .id(1l)
                .cuit(12345678)
                .logo("Bs As Logo")
                .nombre("Empresa Buenos Aires")
                .razonSocial("Sociedad")
                .sucursales(new HashSet<>(Set.of(sucursalLaPlata,sucursalCaba)))
                .build();

        Empresa EmpresaCordoba = Empresa.builder()
                .id(2l)
                .cuit(87654321)
                .logo("Cordoba Logo")
                .nombre("Empresa Cordobeza")
                .razonSocial("Sociedad Anonima")
                .sucursales(new HashSet<>(Set.of(sucursalCordobaCapital,sucursalVillaCarlosPaz)))
                .build();

        sucursalLaPlata.setEmpresa(EmpresaBuenosAires);
        sucursalCaba.setEmpresa(EmpresaBuenosAires);

        sucursalVillaCarlosPaz.setEmpresa(EmpresaCordoba);
        sucursalCordobaCapital.setEmpresa(EmpresaCordoba);


        empresasRepository.save(EmpresaCordoba);
        empresasRepository.save(EmpresaBuenosAires);

        // Muestro todas las empresas
        System.out.println("Todas las empresas:");
        List<Empresa> todasLasEmpresas = empresasRepository.findAll();
        todasLasEmpresas.forEach(System.out::println);

        // Buscar empresa por ID
        Optional<Empresa> empresaEncontrada = empresasRepository.findById(1L);
        empresaEncontrada.ifPresent(e -> System.out.println("Empresa encontrada por ID 1: " + e));

        // Buscar empresa por nombre
        List<Empresa> empresasPorNombre = empresasRepository.genericFindByField("nombre", "Empresa Cordobeza");
        System.out.println("Empresas con nombre 'Empresa Cordobeza':");
        empresasPorNombre.forEach(System.out::println);

        // Actualizar empresa por ID
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa Buenos Aires Actualizada")
                .razonSocial("Razon Social BS AS Actualizada")
                .cuit(222222222)
                .sucursales(EmpresaBuenosAires.getSucursales())
                .build();
        empresasRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresasRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));


        // Eliminar empresa por ID
        empresasRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresasRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 1 ha sido eliminada.");
        }



        // Mostrar todas las empresas restantes
        System.out.println("Todas las empresas después de la eliminación:");
        List<Empresa> empresasRestantes = empresasRepository.findAll();
        empresasRestantes.forEach(System.out::println);
        System.out.println("--------------Mostrar las sucursales de una empresa determinada");
        // Mostrar las sucursales de una empresa deerminada
        Optional<Empresa> empresa = empresasRepository.findById(2L);
        if (empresa.isPresent()) {
            System.out.println("Sucursales de la empresa con ID "  + ":");
            Set<Sucursal> sucursales = empresa.get().getSucursales();
            sucursales.forEach(System.out::println);
        } else {
            System.out.println("Empresa con ID " + " no encontrada.");
        }

    }
}