/**
 * 住所検索
 */

"use strict";
$(function () {
  $("#btn_zipCode").on("click", function () {
    $.ajax({
      url: "https://zipcoda.net/api",
      type: "GET",
      dataType: "json",
      data: {
        zipcode: $("#inputZipcode").val(),
      },
      async: true,
    })
      .done(function (data) {
        $("#inputAddress").val(data.items[0].address);

        let addresses = [];
        let zipcodes = [];
        let ad_co = [];
        for (let i = 0; i < data.items.length; i++) {
          addresses[i] = data.items[i].address;
          zipcodes[i] = data.items[i].zipcode;
          ad_co[i] = addresses[i] + zipcodes[i];
        }
      })
      .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
        alert("fail");
        console.log(XMLHTTPRequest);
        console.log(textStatus);
        console.log(errorThrown);
      });
  });
});
