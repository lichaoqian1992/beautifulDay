<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>授权页面</title>
    <link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/base.css"/>
    
    <script type="text/javascript">
        function goAuth() {
            window.location.href = "http://oauth.qianmi.com/authorize?client_id=${appKey}&response_type=code&redirect_uri=http://172.16.1.36:8090/mLife/callback&state=1&view=web";
        }
    </script>
</head>
<body>
    <div class="margin-20">
        <button type="button" class="btn btn-primary" onclick="goAuth()">授权</button>
    </div>
</body>
</html>
