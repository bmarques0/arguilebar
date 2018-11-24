<?php 
	//volley array php - recebendo arquivos STRING
	require "db_credentials.php"; 

	$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);
 	//$recebendo_array = filter_input_array(INPUT_GET, FILTER_DEFAULT);
	$recebendo_array=$_GET['array'];
	//Conteudo do array
	// ID mesa, id essencia, id bebida, status

	//DEcodificando o array
	$newArray=json_decode($recebendo_array, true);

	$essencias=[];		
	$bebidas=[];
	if(status=="novo"){
		foreach($newArray as $row){
			$sqlPedido = "insert into pedido (`mesa_id_mesa`,`status_id_status`,`funcionario_id_funcionario`) values ('$mesa', '$status');"
			if(!($result = mysqli_query($conn, $sqlPedido))){
				die("Problemas para incluir pedido!<br>".mysqli_error($conn));
			}
			if(essencia>1){
				for(int i=0; i<=$essencias; i++)
				$sqlEssencia = "insert into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('$mesa','.$essencias[i].');"
				if(!($result = mysqli_query($conn, $sqlEssencia))){
					die("Problemas para relacionar essencia ao pedido!<br>".mysqli_error($conn));
				}
			}
			if(bebida>1){
				for(int i=0; i<=$bebidas; i++)
				$sqlBebidas = "insert into pedido_bebida(`pedido_id_pedido`,`essencia_id_essencia`) values ('$mesa','$bebidas[i]');"
				if(!($result = mysqli_query($conn, $sqlBebidas))){
					die("Problemas para relacionar bebida ao pedido!<br>".mysqli_error($conn));
				}
			}
		}
	}elseif(status=="Preparando"){
		foreach($newArray as $row){
			if(is_empty($essencia)){
				if(bebida>1){
					for(int i=0; i<=$bebidas; i++)
					$sqlBebidas = "insert into pedido_bebida(`pedido_id_pedido`,`essencia_id_essencia`) values ('$mesa','$bebidas[i]');"
					if(!($result = mysqli_query($conn, $sqlBebidas))){
						die("Problemas para relacionar bebida ao pedido!<br>".mysqli_error($conn));
					}
				}
			}elseif(is_empty($bebidas)){
				if($essencias>1){
					for(int i=0; i<=$essencias; i++)
					$sqlEssencia = "insert into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('$mesa','$essencias[i]');"
					if(!($result = mysqli_query($conn, $sqlEssencia))){
						die("Problemas para relacionar essencia ao pedido!<br>".mysqli_error($conn));
					}		
				}
			} 
		}
	}


		


?>