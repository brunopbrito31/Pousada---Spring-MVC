<style>
    .top-header{
        background-color: red;
        padding: 2vh;
    }

    .top-header nav p{
        font-size: 25px;
        color: white;
        display: flex;
        justify-content: flex-start;
        align-items: center;
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
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>


<header class="top-header">
    <nav>
        <p>
            <i class="fas fa-portrait"></i><% out.print(request.getSession().getAttribute("user")); %>
        </p>
        <ul>
            <li>
                <a href="/restrict-area/dashboard"><i class="fas fa-home"></i>DashBoard</a>
            </li>
            <li>
                <a href="/"><i class="fas fa-globe"></i> Site</a>
            </li>
            <li>
                <a href="/restrict-area/logoff"><i class="fas fa-sign-out-alt"></i>Deslogar</a>
            </li>
        </ul>
    </nav>
</header>