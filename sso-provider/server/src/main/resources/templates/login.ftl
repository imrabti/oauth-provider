<html>
<head>
    <title>Login - Nuvola OAuth Server</title>

    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>

<div class="container z-depth-3">
    <form role="form" action="login" method="post">
        <div class="row loginWrapper">
            <div class="input-field col s12">
                <i class="material-icons prefix">account_circle</i>
                <input id="username" type="text" class="validate" name="username">
                <label for="username">Login</label>
            </div>
            <div class="input-field col s12">
                <i class="material-icons prefix">https</i>
                <input id="password" type="password" class="validate" name="password">
                <label for="password">Mot de passe</label>
            </div>
        </div>

        <div class="buttonContainer">
            <button type="submit" class="btn btn-primary">Connexion</button>
        </div>
    </form>
</div>
</body>
</html>
