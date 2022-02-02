<style>
    .top-header{
        background-color: red;
        padding: 2vh;
    }

    .top-header nav p{
        font-size: 25px;
        color: white;
    }

    .top-header nav ul{
        list-style: none;
        display: flex;
        justify-content: flex-end;
    }
    .top-header nav ul li{
        margin: 5px;
    }
    .top-header nav ul li a{
        text-decoration: none;
        color: white;
        font-size: 17px;
    }
</style>


<header class="top-header">
    <nav>
        <p>
            Usuário Logado: <% out.print(request.getSession().getAttribute("user")); %>
        </p>
        <ul>
            <li>
                <a href="/restrict-area/dashboard">Home - DashBoard</a>
            </li>
            <li>
                <a href="/">Ir para o Site</a>
            </li>
            <li>
                <a href="/restrict-area/logoff">Deslogar</a>
            </li>
        </ul>
    </nav>
</header>