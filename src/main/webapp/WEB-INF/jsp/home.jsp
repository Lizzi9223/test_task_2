<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <form method="post" action="import" enctype="multipart/form-data">
          File to upload:
          <input type="file" name="file"><br>
          <input type="submit" value="Upload"/>
        </form><br><br>

        <form method="get" action="files">
           <input type="submit" value="Uploaded files"/>
         </form><br><br>
    </body>
</html>