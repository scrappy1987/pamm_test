"use strict";

/** Regular expression for pattern validation. */
var $$dataType = {
    /* Normal surname and forenames */
    NAME: /^[A-Za-z]+$/,

    /** UK date format - does not validate leap year  */
    DATE: /^(0?[1-9]|[12][0-9]|3[01])\/(0?[1-9]|1[012])\/(19\d{2})|([2]\d{3})$/,

    /** UK Government provided regex */
    POSTCODE: /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([AZa-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) [0-9][A-Za-z]{2})$/,

    /** UK national insurance number */
    NI_NUMBER: /^\s*([a-zA-Z]){2}(\s*[0-9]\s*){6}([a-zA-Z]){1}?$/,

    /** Number */
    NUMBER: /^[0-9]*$/,

    //Date in dd/MM/yyyy format only, date validation done in controller
    DATEFORMAT: /[0-9]{2}-[0-9]{2}-[0-9]{4}/,

    EMAIL: /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
};
