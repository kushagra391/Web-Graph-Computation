<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="verticalSearchAssessment.aspx.cs" Inherits="VerticalSearch.verticalSearchAssessment" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Vertical Search</title>
    <link href="css/SlateStyleSheet.min.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .block-ellipsis {
            display: block;
            display: -webkit-box;
            height: 42px;
            font-size: 14px;
            line-height: 1;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .no-pointer-events {
            pointer-events: none;
        }

        .btn-cc {
            width: 50px;
            height: 50px;
            text-align: center;
            padding: 14px 0;
            font-size: 12px;
            line-height: 1.42;
            border-radius: 25px;
            background-color: #ffffff;
            border-color: #ffffff;
            color: #008cba;
            font-weight: bold;
        }

            .btn-cc:hover {
                background-color: #008cba;
                border-color: #008cba;
                color: #ffffff;
            }

        .headsUpLabel {
            background-color: #6e6e6e;
            border-color: #6e6e6e;
            padding: 10px;
            display: block;
            color: #ffffff;
            font-size: 12px;
            border-radius: 10px;
        }

        .queryDropdown {
            display: block;
            background-color: #eeeeee;
            border-color: #008cba;
            font-weight: bold;
            width: 49%;
            height: 40px;
        }
    </style>
    <script src="jquery/jquery-1.11.2.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="jquery/fileHandler.js" type="text/javascript"></script>
    <script type="text/javascript">
        var nextPageCount = 0;
        var prevPageCount = 0;
        var fromPage = 0;
        var pageCount = 0;
        var totalPages = 0;
        $(document).ready(function () {
            $("#tblLoading").hide();
            $("#tblHeadsUp").hide();
            $("#tblNoResults").hide();
            $("#tblResults").hide();
            $("#tblPagination").hide();
            $("#tblNoResults").hide();
            $("#tblError").hide();
        });

        function search(from, caller) {
            $("#tblLoading").show();
            if (caller) {
                fromPage = 0;
            }
            $("#tblHeadsUp").hide();
            $("#tblNoResults").hide();
            $("#tblError").hide();
            $('#tblResults tbody').html('');
            $("#tblResults thead tr>th>h4").html('');
            $("#tblResults thead tr>th").css("border-bottom", "0");
            $("#tblNoResults").removeData();
            $("#tblPagination").hide();
            var searchText = $('#txtSearchBox').val();

            $.ajax({
                url: 'http://localhost:9200/crawled_team/document/_search?pretty=true&q=text:' + searchText + '&from=' + from,
                type: 'GET',
                dataType: 'jsonp',
                timeout: 15000,
                success: function (response) {
                    $("#tblLoading").hide();
                    if (response.hits.hits.length > 0) {
                        $("#tblPagination").show();
                        buildSearchResult(response);
                        $("#tblHeadsUp").show();
                        $("#tblResults").show();
                        $("#tblResults thead tr>th>h4").append("<strong> Search Results ( " + response.hits.total + " hits )</strong>");
                        enableDisablePagingLinks(response);
                    } else {
                        $('#tblResults tbody').html('');
                        $("#tblResults thead tr>th>h4").html('');
                        $("#tblResults").hide();
                        $("#tblHeadsUp").hide();
                        $("#tblNoResults").show();
                        $("#tblPagination").hide();
                    }
                },
                error: function (e) {
                    $("#tblLoading").hide();
                    $("#tblError").show();
                }
            });
        }

        function buildSearchResult(response) {
            for (var i = 0; i < response.hits.hits.length; i++) {
                var docUrl = '<a href=' + response.hits.hits[i]._source.docno + ' target="_blank" style="text-transform: none;text-align:left;display:block"  class="btn btn-primary btn-xs ">' + response.hits.hits[i]._source.docno + '</a>';
                var cleanedText = '<div style="overflow:hidden;height:50px;width:900px;">' + response.hits.hits[i]._source.text + '</div>';
                var ctrl = '<td class="col-md-3"><div class="input-group"><span class="input-group-addon" style="height:108px;border-left:1px solid #cccccc;border-right:1px solid #cccccc"><input type="radio" value="0" name=' + response.hits.hits[i]._source.docno + '>&nbsp;0</input><br/><br/><input type="radio" value="1" name=' + response.hits.hits[i]._source.docno + '>&nbsp;1</input><br/><br/><input type="radio" value="2" name=' + response.hits.hits[i]._source.docno + '>&nbsp;2</input></span></div></td>'
                var template = '<tr><td class="col-md-9">' + docUrl + "<br/><br/>" + cleanedText + '</td>' + ctrl + '</tr>';
                $('#tblResults > tbody:last').append(template);
            }

        }

        function next() {
            fromPage = fromPage + 10;
            search(fromPage, false);

        }

        function prev() {
            fromPage = fromPage - 10;
            search(fromPage, false);
        }

        function enableDisablePagingLinks(response) {
            if (fromPage <= 0) {
                $("#prevPage").addClass('disabled').addClass('no-pointer-events');
            } else {
                $("#prevPage").removeClass('disabled').removeClass('no-pointer-events');
            }

            if (fromPage >= (response.hits.total - 10) && fromPage <= response.hits.total) {
                $("#nextPage").addClass('disabled').addClass('no-pointer-events');
            } else {
                $("#nextPage").removeClass('disabled').removeClass('no-pointer-events');
            }
        }

        function selectQuery() {
            var selectedVal = $("#queryDropdown").val();
            var selectedText = $("#queryDropdown :selected").text();
            if (selectedVal != "-1")
                $('#txtSearchBox').val(selectedText);
            else
                $('#txtSearchBox').val('');
        }

    </script>
</head>
<body>
    <form id="form1" runat="server">

        <table class="table text-center">
            <tr>
                <th class="text-center" style="background-color: #008cba; color: #ffffff; height: 150px; vertical-align: middle">
                    <h2><strong>Vertical Search</strong></h2>
                </th>
            </tr>
            <tr>
                <td>

                    <select id="queryDropdown" class="queryDropdown" onchange="javascript:selectQuery();" style="float: left;">
                        <option value="-1">--Select Query--</option>
                        <option value="152501">recent immigration order</option>
                        <option value="152502">immigration 20th century</option>
                        <option value="152503">illegal immigration</option>
                    </select>
                    <select id="assessorDropdown" class="queryDropdown" style="float:right;">
                        <option value="-1">--Select Accessor--</option>
                        <option value="Darshan">Darshan</option>
                        <option value="Kushagra">Kushagra</option>
                        <option value="Sagar">Sagar</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="input-group">
                        <span class="input-group-addon"></span>
                        <input type="text" id="txtSearchBox" class="form-control" />
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="button" onclick="search(0, true);" name="btnSearch">Search</button>
                        </span>
                    </div>
                </td>
            </tr>
        </table>
        <table id="tblLoading" class="table text-center" align="center">
            <tr>
                <td>
                    <img src="loading.GIF" alt="Loading..." /><br />
                    <h5><strong>Loading...</strong></h5>
                </td>
            </tr>
        </table>
        <table id="tblHeadsUp" class="table text-center" align="center" style="width: 75%">
            <tr>
                <td>
                    <span class="label label-default headsUpLabel"><strong>Heads up !! Relevance grading for each document is done page by page. 
                        So hit the button below to submit grades for all documents listed on this page.</strong><br />
                        <br />
                        <a href="javascript:return false;" class="btn btn-primary btn-cc" onclick="writeToQrel();">Submit</a></span>

                </td>
            </tr>
        </table>
        <table id="tblNoResults" align="center" style="width: 75%; display: none;" class="table">
            <tbody>
                <tr>
                    <td style="border: 0">
                        <div class="alert alert-dismissible alert-danger">
                            <strong>No Results Found!</strong>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <table id="tblError" align="center" style="width: 75%; display: none;" class="table">
            <tbody>
                <tr>
                    <td style="border: 0">
                        <div class="alert alert-dismissible alert-danger">
                            <strong>Oh snap! Something went wrong while retrieving. Make sure Elastic Search is turned ON and try submitting again.</strong>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <table id="tblResults" align="center" style="width: 75%" class="table table-hover">
            <thead>
                <tr>
                    <th colspan="2">
                        <h4></h4>
                    </th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <table id="tblPagination" class="table text-center" style="width: 75%" align="center">
            <tr>
                <td>
                    <ul class="pager">
                        <li id="prevPage"><a href="javascript:return false;" onclick="javascript: prev();"><strong>Prev</strong></a></li>
                        <li id="nextPage"><a href="javascript:return false;" onclick="javascript: next();"><strong>Next</strong></a></li>
                    </ul>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
