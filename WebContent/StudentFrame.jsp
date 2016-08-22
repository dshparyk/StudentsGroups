<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Students List</title>
</head>
<body>
	<form action="<c:url value="/edit"/>" method="POST">
		<input type="hidden" name="studentId" value="${student.studentId }"/>
		<table>
			<tr>
				<td>LastName:</td><td><input type="text" name="lastName" value="${student.lastName}"/></td>
			</tr>
			<tr>	
				<td>FirstName:</td><td><input type="text" name="firstName" value="${student.firstName}"/></td>
			</tr>
			<tr>	
				<td>DateOfBirth:</td><td><input type="text" name="dateOfBirth" value="${student.dateOfBirth}"/></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td>
				<c:choose>
					<c:when test="${student.sex==0}">
						<input type="radio" name="sex" value="0" checked>M</input>
						<input type="radio" name="sex" value="1">F</input>
					</c:when>
					<c:otherwise>
						<input type="radio" name="sex" value="0">M</input>
						<input type="radio" name="sex" value="1" checked>F</input>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>Group:</td>
				<td>
					<select name="groupId">
					<c:forEach var="group" items="${student.groups}">
						<c:choose>
							<c:when test="${group.groupId==student.groupId}">
								<option value="${group.groupId}" selected><c:out value="${group.nameGroup}"/></option>
							</c:when>
							<c:otherwise>
								<option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>YearOfStudy:</td><td><input type="text" name="educationYear" value="${student.educationYear}"/></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="submit" value="OK" name="OK"/></td>
				<td><input type="submit" value="Cancel" name="Cancel"/></td>
			</tr>
		</table>
	</form>
</body>
</html>