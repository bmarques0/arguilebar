<?php

function soNumero($str) {
    return preg_replace("/[^0-9]/", "", $str);
}



?>

<script>
function MascaraCPF(cpf){
        if(mascaraInteiro(cpf)==false){
                event.returnValue = false;
        }       
        return formataCampo(cpf, '000.000.000-00', event);
}
</script>