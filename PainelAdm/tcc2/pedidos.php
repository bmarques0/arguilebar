<?php

require 'db_credentials.php';
require "authenticate.php";


if(!$login){
  header("Location: " . dirname($_SERVER['SCRIPT_NAME']) . "/login.php");}

$conn = mysqli_connect($servername, $username, $password, $dbname, $port, $socket);

if (!$conn) {
  die("Problemas ao conectar com o BD!<br>".
       mysqli_connect_error());
}

      $sql = "Select mm.nomeMesa, 
  ee.sabor,
  bb.marca,
    mm.descricao, mm.id_mesa, mm.mesa_id_mesa
 FROM(
  (select m.nomeMesa, m.id_mesa, e.sabor, s.descricao, b.marca, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida ORDER BY m.nomeMesa) AS mm,
  (select m.nomeMesa, m.id_mesa, s.descricao, group_concat(b.marca ORDER BY marca separator ' + ') as marca, p.mesa_id_mesa from mesa m, statuspedido s, pedido p, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida GROUP BY m.nomeMesa) AS bb,
  (select m.nomeMesa, m.id_mesa, group_concat(e.sabor ORDER BY sabor separator ' + ') as sabor, s.descricao, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status GROUP BY m.nomeMesa) AS ee)
WHERE mm.id_mesa=bb.id_mesa and ee.id_mesa=mm.id_mesa
GROUP BY nomeMesa";


if (isset($_GET["preparar"]) == "preparar") {
    $id=$_GET["preparar"];
    $sql="UPDATE pedido set pedido.status_id_status='2' where pedido.mesa_id_mesa=$id";
    if(!mysqli_query($conn,$sql)){
            die("Problemas para alterar status do pedido!<br>".
                 mysqli_error($conn));
    }
    $sql = "Select mm.nomeMesa, 
  ee.sabor,
  bb.marca,
    mm.descricao, mm.id_mesa, mm.mesa_id_mesa
 FROM(
  (select m.nomeMesa, m.id_mesa, e.sabor, s.descricao, b.marca, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida ORDER BY m.nomeMesa) AS mm,
  (select m.nomeMesa, m.id_mesa, s.descricao, group_concat(b.marca ORDER BY marca separator ' + ') as marca, p.mesa_id_mesa from mesa m, statuspedido s, pedido p, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida GROUP BY m.nomeMesa) AS bb,
  (select m.nomeMesa, m.id_mesa, group_concat(e.sabor ORDER BY sabor separator ' + ') as sabor, s.descricao, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status GROUP BY m.nomeMesa) AS ee)
WHERE mm.id_mesa=bb.id_mesa and ee.id_mesa=mm.id_mesa
GROUP BY nomeMesa";

}elseif(isset($_GET["finalizar"]) == "finalizar"){
    $id=$_GET["finalizar"];
    $sql="UPDATE pedido set pedido.status_id_status='3' where pedido.mesa_id_mesa=$id";
    if(!mysqli_query($conn,$sql)){
            die("Problemas para alterar status do pedido!<br>".
                 mysqli_error($conn));
    }
    $sql = "Select mm.nomeMesa, 
  ee.sabor,
  bb.marca,
    mm.descricao, mm.id_mesa, mm.mesa_id_mesa
 FROM(
  (select m.nomeMesa, m.id_mesa, e.sabor, s.descricao, b.marca, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida ORDER BY m.nomeMesa) AS mm,
  (select m.nomeMesa, m.id_mesa, s.descricao, group_concat(b.marca ORDER BY marca separator ' + ') as marca, p.mesa_id_mesa from mesa m, statuspedido s, pedido p, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
  and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida GROUP BY m.nomeMesa) AS bb,
  (select m.nomeMesa, m.id_mesa, group_concat(e.sabor ORDER BY sabor separator ' + ') as sabor, s.descricao, p.mesa_id_mesa from mesa m, essencia e, statuspedido s, pedido p, pedido_essencia pe where p.mesa_id_mesa=m.id_mesa
  and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and s.id_status=p.status_id_status GROUP BY m.nomeMesa) AS ee)
WHERE mm.id_mesa=bb.id_mesa and ee.id_mesa=mm.id_mesa
GROUP BY nomeMesa";
}

if(!($pedidos = mysqli_query($conn,$sql))){
  die("Problemas para carregar as Essencias do BD!<br>".
    mysqli_error($conn));
  }

$sql = "select * from funcionario";
if(!($funcionarios = mysqli_query($conn,$sql))){
  die("Problemas para carregar as Essencias do BD!<br>".
    mysqli_error($conn));
  }


?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Hookah Bar</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Customização CSS -->
    <link rel="stylesheet" href="styles.css">
    <!-- javascripts -->
    <script src="js/scripts.js"></script>

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
</head>

<body  onload="setInterval('AtualizaBD()', 5000)">
  <div class="container-wrapper">    
    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

        <a class="navbar-brand mr-1" href="pedidos.php">Hookah Bar</a>
        <!-- Navbar Search -->
        <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Procurar..." aria-label="Procurar" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <button class="btn btn-primary" type="button">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </div>
        </form>

        <!-- Navbar -->
        <ul class="navbar-nav ml-auto ml-md-0">
          <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
              <a class="dropdown-item" href="#">Usuários</a>
              
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="logout.php">Logout</a>
            </div>
          </li>
        </ul>
    </nav> <!-- FiM DO NAVBAR SUPERIOR -->


    <div id="wrapper">
      <div class="row">
      <div class="col-md-3" id="navloca">
         <nav id="sidebar">
            <!-- <div class="sidebar-header">
                <h3>Hookah Bar</h3>
            </div> -->

            <ul class="sidebar navbar-nav">
                <li>
                    <a href="pedidos.php">Pedidos</a>
                </li>

                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown">Produtos</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li>
                            <a href="carvao.php">Carvão</a>
                        </li>
                        <li>
                            <a href="aluminio.php">Alumínio</a>
                        </li>
                        <li>
                            <a href="Essencia.php">Essência</a>
                        </li>
                        <li>
                            <a href="rosh.php">Rosh</a>
                        </li>
                        <li>
                            <a href="steam.php">Steam</a>
                        </li>
                        <li>
                            <a href="mangueira.php">Mangueira</a>
                        </li>
                        <li>
                            <a href="vaso.php">Vaso</a>
                        </li>
                        <li>
                            <a href="bebida.php">Bebidas</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="mesas.php">Mesas</a>
                </li>
                <li>
                    <a href="funcionario.php">Funcionário</a>
                </li>
                <li>
                    <a href="relatorio.php">Relatório</a>
                </li>
            </ul>
        </nav>
      </div>    
        <!-- Início conteudo -->
      <div class="col-md-9" id="padding">  
        <div id="content-wrapper">      
          <div class="row">
            <div class="container-fluid">  
              <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="pedidos.php">Dashboard Pedido</a>
                  </li>
                  <li class="breadcrumb-item active">Pedidos</li>
              </ol>
            </div>
          </div>   
          <div class="card">
                  <div class="card-header">
                    <!-- <i class="fas fa-table"></i> -->
                    <h3>Pedidos</h3>
                  </div>
               
                  <div class="card-body">
                      <form  method="GET" action="#" > 
                        <div class="row">
                          <div  class="container-fluid">
                            <!-- <div class="card mb-3"> -->                    
                             <!-- <div class="card-header">
                                <h3>Pesquisar</h3>
                              </div> -->
                            <div class="row">
                              <table class="table table-bordered" id="dataTable"  cellspacing="0">
                                <thead>
                                  <tr>
                                    <th>Mesa</th>
                                    <th>Arguile</th>
                                    <th>Bebida</th>
                                    <th>Status</th>
                                    <th>Ação</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <?php if(isset($pedidos) ) { ?>
                                    <?php if((mysqli_num_rows($pedidos) > 0) ): ?>
                                      <?php while($uni = mysqli_fetch_assoc($pedidos) ): ?> 
                                        <?php echo '<tr><td>'. $uni["nomeMesa"] . '</td><td>' . $uni["sabor"] . '</td><td>' . $uni["marca"] . '</td><td>' . $uni["descricao"] . '</td><td>' . '<button class="btn btn-primary" type="submit" id="preparar" class="floated" name="preparar" value='.$uni["mesa_id_mesa"].'>Preparar</button>' . ' ' . '<button class="btn btn-primary" type="submit" id="finalizar" class="floated" name="finalizar" value='.$uni["mesa_id_mesa"].'>Finalizar</button>' . '</td></tr>' ?>

                                      <?php endwhile; ?>
                                    <?php else: ?>
                                      Nenhuma busca realizada!
                                    <?php endif; ?>
                                  <?php } ?> 
                                </tbody>
                              </table>
                                        
                            <!-- FIM DIV ESCONDIDA -->
                           
                            </div> <!-- div row form -->
                          </div> <!-- container fluid-->
                        </div>  
                      </form>
                    </div> <!-- Card body -->
                  </div>  <!-- card -->
            </div>  <!--content-wrapper -->
          </div>   <!-- col-md-9 -->
        </div> <!--row-->  
      </div> <!-- ID WRAPPER-->
    </div>    <!-- container-wrapper -->

    <!-- jQuery CDN - Slim version (=without AJAX) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

</body>

</html>