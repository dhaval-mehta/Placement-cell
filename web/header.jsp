<%@taglib prefix="my" uri="/WEB-INF/tlds/myTags" %>


<header id="header">

    <nav class="navbar navbar-inverse" role="banner">
        <div class="container">
            <div class="navbar-header" style="padding-left: 20px">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <center><a class="navbar-brand" href="index.html"><img src="/images/logo.png" alt="logo" height="40px"></a></center><div class="container" style="margin-top :20px;margin-left: 40px;font-family: verdana;font-size:25px;color: white" > Placement Cell</div>
            </div>
        </div>
    </nav>

    <nav class="navbar navbar-inverse" role="banner">
        <div class="container">
            <div class="navbar-header collapse navbar-collapse">
                <ul class="nav navbar-nav">

                    <my:AddNavigation />

                </ul>
            </div>
        </div><!--/.container-->
    </nav><!--/nav-->

</header><!--/header-->

