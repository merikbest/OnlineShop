<#import "parts/common.ftlh" as c>

<@c.page>
    List of users
    <input type="button" value="на главную" onClick='location.href="/main"'>
    <table>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>