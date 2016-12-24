$('#btnRun').click(function ()
{
    var tipo_servico =
            {
                descricao: $('#txDescricao').val(),
                inativo: false
            };
            
    var url = "/salaodorusso/tserv-save";
    
    $.post(url, tipo_servico, function (result)
    {
       // alert(result.msg); 
    });
});