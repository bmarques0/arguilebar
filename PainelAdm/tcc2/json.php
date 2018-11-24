<?php 
$array = array(
    'titulo' => 'noticia 1',
    'corpo' => 'corpo da noticia 1',
    'data' => '02/07/2014'
);

$array=json_encode($array);
echo '{"essencia":'.$array.'}'; 

?>