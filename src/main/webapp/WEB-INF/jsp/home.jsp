<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Home</title>        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="<c:url value="/css/style.css" />">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <style>            
            
            .loader {
              display: none;
              border: 8px solid #f3f3f3;
              border-radius: 50%;
              border-top: 8px solid #3498db;
              width: 30px;
              height: 30px;              
            }
            
            .animate{
                display: block;
                -webkit-animation: spin 2s linear infinite; /* Safari */
              animation: spin 2s linear infinite;
            }

            /* Safari */
            @-webkit-keyframes spin {
              0% { -webkit-transform: rotate(0deg); }
              100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
              0% { transform: rotate(0deg); }
              100% { transform: rotate(360deg); }
            }
            
            .my {
              width: 0.1px;
              height: 0.1px;
              opacity: 0;
              overflow: hidden;
              position: absolute;
              z-index: -1;
            }

            .label {
                width: 170px;
                height: 40px;
                border-radius: 4px;
                text-align: center;
                vertical-align: middle;
                cursor: pointer;
                display: block;
                font: 14px/50px Tahoma;
                border: 1px solid green;
                color: black;
            }

            .label:hover {
                color: white;
                background: green;
            }
            
        </style>
        
        <script>
            $(function(){
                $('#upload_btn').click(function(){
                    e1 = $('#target');
                    e1.addClass('animate');
                    e1.one('webkitAnimationEnd oanimationend msAnimationEnd animationend',
                    function (e) {
                        e1.removeClass('animate');
                    });
                });
                $('.my').change(function() {
                    if ($(this).val() != '') $(this).prev().text('Files chosen: ' + $(this)[0].files.length);
                    else $(this).prev().text('Choose files');
                });
            });
        </script>
    </head>
    <body>
        <br>
        <form style="margin-left: 20px" method="post" action="import" enctype="multipart/form-data">
            <label style="float: left; margin-right: 15px" for="myfile" class="label">Choose files</label>
            <input type="file" class="my" id="myfile" name="file">
          <input id="upload_btn" class="btn" type="submit" value="Upload"/><br>
          <div id="target" class="loader"></div>
        </form><br><br><br>

        <form style="margin-left: 20px" method="get" action="files">
           <input class="btn" type="submit" value="See uploaded files"/>
         </form><br><br>
    </body>
</html>