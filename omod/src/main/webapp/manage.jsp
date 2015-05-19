<%--
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
&lt;%&ndash;<openmrs:htmlInclude file="/moduleResources/css/dataTables.css" />&ndash;%&gt;
<openmrs:htmlInclude file="/moduleResources/patientdetails/js/jquery.dataTables.js" />

<script type="text/javascript">
    $j(document).ready(function() {
        $j('#myTable').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
    } );
</script>
<table id="myTable">
    <thead>
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Identifier</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="patient" items="${patients}" varStatus="status">
        <tr>
            <td><a href="encounter.form?patientId=${patient.patientId}">${patient.givenName}</a></td>
            <td>${patient.age}</td>
            <td>${patient.gender}</td>
            <td>${patient.identifiers}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<form  action ="<c:url value='/manage'>" method="post">
    <table>
        <tr><td>first name</td><td><input type="text" name="fname" placeholder="enter first name"></td></tr>
        <tr><td>middle name</td><td><input type="text" name="mname" placeholder="enter middle name"></td></tr>
        <tr><td>last name</td><td><input type="text" name="lname" placeholder="enter last name"></td></tr>
        <button> Create</button>
    </table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>
--%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<form  action ="<c:url value='/module/ongezamgonjwa/addpatient.form'/>" method='post'>
    <table>
        <tr><td>first name</td><td><input type="text" name="fname" placeholder="enter first name"></td></tr>
        <tr><td>middle name</td><td><input type="text" name="mname" placeholder="enter middle name"></td></tr>
        <tr><td>last name</td><td><input type="text" name="lname" placeholder="enter last name"></td></tr>
        <button> Create</button>
    </table>
</form>