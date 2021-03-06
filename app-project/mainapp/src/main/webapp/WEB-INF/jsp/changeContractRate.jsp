<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/mainmenu.jspf" %>

<section>
    <div class="container">
        <div class="well">CHANGE RATE FOR ${account.login}</div>
        <div class="alert alert-danger" id="errorMessage">
        </div>
        <form role="form-inline" class="allRatesList">
            <div class="form-group">
                <table class="table table-hover">
                    <thead class="rates-table-thead"></thead>
                    <tbody class="rates-table-tbody"></tbody>
                </table>
            </div>
        </form>
    </div>
</section>
<script src="static/scripts/changeContractRate.js"></script>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
