
$('#btnLogin').click(function ()
{
    var usuario =
            {
                nome: 'Teste',
                senha: '44s8da'
            };
    
    var url = "/salaodorusso/usr-login";
    $.get(url, usuario, function (result)
    {
        alert(result.msg); 
    });

});