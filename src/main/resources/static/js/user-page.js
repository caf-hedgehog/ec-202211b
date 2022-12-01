/**
 * 住所検索
 */

"use strict";
$(function () {
  $("#btn_name").on("click", function () {
    const input = document.getElementById("userName");
    input.disabled = false;
  });

  $("#btn_email").on("click", function () {
    const input = document.getElementById("email");
    input.disabled = false;
  });

  $("#btn_zipcode1").on("click", function () {
    const input = document.getElementById("inputZipcode");
    input.disabled = false;
    const btn = document.getElementById("btn_zipCode");
    btn.disabled = false;
  });

  $("#btn_address").on("click", function () {
    const input = document.getElementById("inputAddress");
    input.disabled = false;
  });

  $("#btn_telephone").on("click", function () {
    const input = document.getElementById("telephone");
    input.disabled = false;
  });
});
