<?php 
	//volley array php - recebendo arquivos STRING
	require "db_credentials.php"; 

	$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);
 	//$recebendo_array = filter_input_array(INPUT_GET, FILTER_DEFAULT);
	
	//$recebendo_array=$_POST['array'];
	//Conteudo do array
	// ID mesa, id essencia, id bebida, status

	//DEcodificando o array
	//$newArray=json_decode($recebendo_array, true);

	//$id_essencia1=null;
	//$id_essencia2=null;
	//$bebida1=null;
	//$bebida2=null;
	//$bebida3=null;
	//$bebida4=null;
	//$bebida5=null;

	//$stmt = $conn->prepare("insert into pedido values(?,?)");
	$id_mesa=5;
	$id_status=1;


	$id_essencia1=$_POST['idEssencia1'];
	$id_essencia2=$_POST['idEssencia2'];
	$bebida1=$_POST['idBebida'];
	$bebida2=$_POST['idBebida2'];
	$bebida3=$_POST['idBebida3'];
	$bebida4=$_POST['idBebida4'];
	$bebida5=$_POST['idBebida5'];

	//$id_essencia1 = intval($id_essencia1);
	//$id_essencia2 = intval($id_essencia2);
	//$usuario = intval($usuario);

	$sql = "SELECT * from pedido where mesa_id_mesa='$id_mesa'";
	$result = mysqli_query($conn, $sql);      //executa a query no banco de dados
    
    if(mysqli_num_rows($result) > 0){
    	$sql = "UPDATE pedido set pedido.status_id_status='$id_status' where pedido.mesa_id_mesa='$id_mesa'";
    	if(!($result = mysqli_query($conn, $sql))){
			die("Problemas para atualizar Status do pedido!<br>".mysqli_error($conn));
		}	
    }else{
    	//print_r("Entrou no insert");
    	$sql = "INSERT INTO pedido(`mesa_id_mesa`,`stauts_id_status`) values ('$id_mesa','$id_status')";
    	if(!($result = mysqli_query($conn, $sql))){
			die("Problemas para inserir nova mesa!<br>".mysqli_error($conn));
		}	
    }                                
	if($id_essencia2==null && $id_essencia1!=null){ //Se for solicitado apenas UMA essência
		//Inclue o pedido de essencia na tabela
		//print_r("Entrou na SELEÇÃO DE UMA ESSENCIA");
		$sqlEssencia = "INSERT into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('$id_mesa','$id_essencia1');";
		if(!($result = mysqli_query($conn, $sqlEssencia))){
			die("Problemas para relacionar essencia ao pedido!<br>".mysqli_error($conn));
		}
		
		//Diminui a quantidade de essencia
		$sqlquantidadeEssencia = "UPDATE essencia set essencia.quantidade=essencia.quantidade-1 where essencia.id_essencia='$id_essencia1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeEssencia))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
				
	}if($id_essencia2!=null && $id_essencia1!=null ){//Se for solicitado DUAS essênciaS
		
		$sqlEssencia1 = "INSERT into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('$id_mesa','$id_essencia1');";
		if(!($result = mysqli_query($conn, $sqlEssencia1))){
			die("Problemas PARA INCLUIR ESSÊNCIA 1!<br>".mysqli_error($conn));
		}
		
		$sqlquantidadeEssencia1 = "UPDATE essencia set essencia.quantidade=essencia.quantidade-0.5 where essencia.id_essencia='$id_essencia1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeEssencia1))){
			die("Problemas para diminuir quantidade DA ESSENCIA 1!<br>".mysqli_error($conn));
		}
		
		$sqlEssencia2 = "INSERT into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('$id_mesa','$id_essencia2');";
		if(!($result = mysqli_query($conn, $sqlEssencia2))){
			die("Problemas para relacionar essencia ao pedido!<br>".mysqli_error($conn));
		}
		
		$sqlquantidadeEssencia2 = "UPDATE essencia set essencia.quantidade=essencia.quantidade-0.5 where essencia.id_essencia='$id_essencia2'";
		if(!($result = mysqli_query($conn, $sqlquantidadeEssencia2))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
			
	}

	/*
	if($bebida5!=null){ //POSSUI 5 BEBIDAS
		
		//Inserindo as bebidas
		$sqlBebida1 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida1');";
		if(!($result = mysqli_query($conn, $sqlBebida1))){
			die("Problemas para relacionar bebida 1 de 5 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida2 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida2');";
		if(!($result = mysqli_query($conn, $sqlBebida2))){
			die("Problemas para relacionar bebida 2 de 5 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida3 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida3');";
		if(!($result = mysqli_query($conn, $sqlBebida3))){
			die("Problemas para relacionar bebida 3 de 5 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida4 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida4');";
		if(!($result = mysqli_query($conn, $sqlBebida4))){
			die("Problemas para relacionar bebida 4 de 5 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida5 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida5');";
		if(!($result = mysqli_query($conn, $sqlBebida5))){
			die("Problemas para relacionar bebida 5 de 5 ao pedido!<br>".mysqli_error($conn));
		}

		//Atualizando a quantidade

		$sqlquantidadeBebida1 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida1))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida2 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida2'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida2))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida3 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida3'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida3))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida4 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida4'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida4))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida5 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida5'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida5))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}		

	}	
	if($bebida5==null && $bebida4!=null){

		//Inserindo as bebidas
		$sqlBebida1 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida1');";
		if(!($result = mysqli_query($conn, $sqlBebida1))){
			die("Problemas para relacionar bebida 1 de 4 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida2 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida2');";
		if(!($result = mysqli_query($conn, $sqlBebida2))){
			die("Problemas para relacionar bebida 2  de 4 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida3 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida3');";
		if(!($result = mysqli_query($conn, $sqlBebida3))){
			die("Problemas para relacionar bebida 3 de 4 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida4 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida4');";
		if(!($result = mysqli_query($conn, $sqlBebida4))){
			die("Problemas para relacionar bebida 4 de 4 ao pedido!<br>".mysqli_error($conn));
		}

		//Atualizando quantidade
		$sqlquantidadeBebida1 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida1))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida2 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida2'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida2))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida3 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida3'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida3))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida4 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida4'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida4))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}


	}*/
	if($bebida4==null && $bebida3!=null){

		//Inserindo as bebidas
		$sqlBebida1 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida1');";
		if(!($result = mysqli_query($conn, $sqlBebida1))){
			die("Problemas para relacionar bebida 1 de 3 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida2 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida2');";
		if(!($result = mysqli_query($conn, $sqlBebida2))){
			die("Problemas para relacionar bebida 2 de 3 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida3 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida3');";
		if(!($result = mysqli_query($conn, $sqlBebida3))){
			die("Problemas para relacionar bebida 3 de 3 ao pedido!<br>".mysqli_error($conn));
		}

		//Atualizando quantidade
		$sqlquantidadeBebida1 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida1))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida2 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida2'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida2))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida3 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida3'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida3))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}


	}
	if($bebida3==null && $bebida2!=null){

		//Inserindo as bebidas
		$sqlBebida1 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida1');";
		if(!($result = mysqli_query($conn, $sqlBebida1))){
			die("Problemas para relacionar bebida 1 de 2 ao pedido!<br>".mysqli_error($conn));
		}
		$sqlBebida2 = "insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida2');";
		if(!($result = mysqli_query($conn, $sqlBebida2))){
			die("Problemas para relacionar bebida 2 de 2 ao pedido!<br>".mysqli_error($conn));
		}

		//Atualizando quantidade
		$sqlquantidadeBebida1 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida1))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}
		$sqlquantidadeBebida2 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida2'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida2))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}


	}
	if($bebida2==null && $bebida1!=null){

		//Inserindo as bebidas
		$sqlBebida1 = "INSERT into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('$id_mesa','$bebida1');";
		if(!($result = mysqli_query($conn, $sqlBebida1))){
			die("Problemas para relacionar bebida 1 de 1 ao pedido!<br>".mysqli_error($conn));
		}

		//Atualizando quantidade
		$sqlquantidadeBebida1 = "UPDATE bebida set bebida.quantidade=bebida.quantidade-1 where bebida.id_bebida='$bebida1'";
		if(!($result = mysqli_query($conn, $sqlquantidadeBebida1))){
			die("Problemas para diminuir quantidade!<br>".mysqli_error($conn));
		}

	}




?>