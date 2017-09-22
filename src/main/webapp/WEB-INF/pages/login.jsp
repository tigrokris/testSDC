<html>
<head><title>Register Page</title></head>
<body>
<h3>Login with Email and Password</h3>

<form name='f' action='spring_security_check' method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
        </tr>
    </table>
</form>
<h3>${message1}</h3>

<form name='f1' action='/testSDC/register' modelAttribute="userTo" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='name' value=''></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type='email' name='email'/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit1" type="submit" value="Register"/></td>
        </tr>
    </table>
</form>
</body>
</html>