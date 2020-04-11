<#import "parts/common.ftlh" as c>
<#--<#import "parts/unit.ftlh" as u>-->

<@c.page>
    <div class="container">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100"
                         src="https://i.pinimg.com/originals/a6/51/85/a65185f3621017135eb2486774849dcc.jpg"
                         alt="First slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <#list perfumes as perfume>

        <div class="card" style="width: 18rem;">
            <#if perfume.filename??>
                <img src="/img/${perfume.filename}" class="card-img-top">
            </#if>
            <div class="card-body">
                <h5 class="card-title">${perfume.perfumeTitle}</h5>
                <p class="card-text">${perfume.perfumer}</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </#list>
<#--        <#list perfumes as perfume>-->
<#--            <p>${perfume.getId()}</p>-->

<#--        </#list>-->
</@c.page>