(function ($) {

  const listarCiudades = (value = null, opcion = null) => {
    if ($('.listaciudades').length) {
      $('.listaciudades').children().remove();
    }
    $.ajax({
      url: "./ciudades/buscar",
      type: 'POST',
      data: {
        buscar: value,
        opcion: opcion
      },
      success: (data) => {
        if (data !== 'false') {
          let dataTable = JSON.parse(data);
          $('.sinresultados').html('');
          dataTable.map((fila) => {
            $('.listaciudades').append(`
              <tr>
                <td>${fila.codigo}</td>
                <td>${fila.ciudad}</td>
                <td>${fila.pais}</td>
                <td>${fila.clima}</td>
                <td>${fila.fechaCreacion}</td>
                <td>
                  <button type="button" class="btn btn-info btn-editar" data-info='` + JSON.stringify(fila) + `'>Editar</button>
                </td>
                <td>
                  <button type="button" class="btn btn-danger btn-eliminar" data-cod='` + fila.codigo + `'>Eliminar</button>
                </td>
              </tr>
            `);
          });
        } else {
          $('.sinresultados').html(`
            No se encontraron resultados.
          `);
        }
      }
    });
  };

  $(document).on('click', '.btn-agregar', () => {
    let dataForm = $('#frm-crearciudad').serializeArray();
    $.ajax({
      url: "./ciudades/agregar",
      type: 'POST',
      data: dataForm,
      success: (data) => {
        alert(data);
        listarCiudades();
      }
    });
    
    $('#frm-crearciudad')[0].reset();
    $('#frm-crearciudad input[name=codigo]').focus();
    $('#frm-crearciudad input[name=codigo]').select();
  });

  $(document).on('click', '.btn-buscar', function (e) {
    cadena = $('.txt-buscar').val();
    opcion = $('.busqueda').val();
    console.log(opcion)
    if (cadena.length > 0) {
      listarCiudades(cadena, opcion);
    }
  });

  $(document).on('click', '.btn-all', function (e) {
    listarCiudades();
    $('.txt-buscar').val('');
    $('.txt-buscar').focus();
    $('.txt-buscar').select();
  });

  $(document).on('click', '.btn-editar', function (e) {
    let data = JSON.parse($(this).attr('data-info'));
    $('#frm-crearciudad input[name=codigo]').val(data.codigo);
    $('#frm-crearciudad input[name=ciudad]').val(data.ciudad);
    $('#frm-crearciudad input[name=pais]').val(data.pais);
    $('#frm-crearciudad input[name=clima]').val(data.clima);

    $('.btn-agregar').addClass('btn-actualizar');
    $('.btn-agregar').text('Actualizar');
    $('.btn-agregar').removeClass('btn-agregar');

    if ($('.btn-cancelar').length === 0) {
      $('.accionprincipal').append(`<button type="button" class="btn-cancelar">Cancelar</button>`);
    }

  });

  $(document).on('click', '.btn-cancelar', function (e) {
    $(this).remove();
    $('#frm-crearciudad')[0].reset();
    $('#frm-crearciudad input[name=codigo]').focus();
    $('#frm-crearciudad input[name=codigo]').select();

    $('.btn-actualizar').addClass('btn-agregar');
    $('.btn-actualizar').text('Agregar');
    $('.btn-actualizar').removeClass('btn-actualizar');
  });

  $(document).on('click', '.btn-actualizar', function () {
    let dataForm = $('#frm-crearciudad').serializeArray();

    $.ajax({
      url: "./ciudades/actualizar",
      type: 'POST',
      data: dataForm,
      success: (data) => {
        alert(data);
        listarCiudades();
      }
    });
    $('.btn-cancelar').remove();
    $('.btn-actualizar').addClass('btn-agregar');
    $('.btn-actualizar').text('Agregar');
    $('.btn-actualizar').removeClass('btn-actualizar');
    
    $('#frm-crearciudad')[0].reset();
    $('#frm-crearciudad input[name=codigo]').focus();
    $('#frm-crearciudad input[name=codigo]').select();
  });

  $(document).on('click', '.btn-eliminar', function (e) {
    let codDelete = $(this).attr('data-cod');
    let result = confirm("¿Seguro desea borrar la ciudad?");

    if (result) {
      $.ajax({
        url: "./ciudades/eliminar",
        type: 'POST',
        data: {
          codDelete: codDelete
        },
        success: (data) => {
          listarCiudades();
        }
      });
    }
  });


  listarCiudades();
})(jQuery);