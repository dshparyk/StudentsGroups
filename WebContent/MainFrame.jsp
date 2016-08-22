<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Students List</title>
</head>
<body>
	<form action="<c:url value="/main"/>" method="POST">
		<table>
			<tr>
				<td>Year:<input type="text" name="year" value="${form.year}"/><br/></td>
				<td>Groups List:
					<select name="groupId">
						<c:forEach var="group" items="${form.groups}">
							<c:choose>
								<c:when test="${group.groupId==form.groupId}">
									<option value="${group.groupId}" selected><c:out value="${group.nameGroup}"/></option>
								</c:when>
								<c:otherwise>
									<option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<td><input type="submit" name="getList" value="Update"/></td>
			</tr>
		</table>
		
		<p/>
		<b>Students List for selected parameters:</b>
		<br/>
		<table>
			<tr>
				<th> </th>
				<th>LastName</th>
				<th>FirstName</th>
			</tr>
			<c:forEach var="student" items="${form.students }">
			<tr>
				<td><input type="radio" name="studentId" value="${student.studentId}"></td>
                <td><c:out value="${student.lastName}"/></td>
                <td><c:out value="${student.firstName}"/></td>
			</tr>
			</c:forEach>
		</table>
		
		<table>
			<tr>
				<td><input type="submit" value="Add" name="Add"/></td>
				<td><input type="submit" value="Edit" name="Edit"/></td>
				<td><input type="submit" value="Delete" name="Delete"/></td>
			</tr>
		</table>
		
		<p/>
		<b>Move Students to Group</b>
		<br/>
		<table>
			<tr>
				<td>Year:<input type="text" name="newYear" value="${form.year}"/><br/></td>
				<td>Groups List:
					<select name="newGroupId">
						<c:forEach var="group" items="${form.groups}">
							<option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
						</c:forEach>
					</select>
					</td>
					<td><input type="submit" name="MoveGroup" value="Move"/></td>
                </tr>
            </table>
	</form>
</body>
</html>