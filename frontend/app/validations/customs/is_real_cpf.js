// import buildMessage from 'ember-changeset-validations/utils/validation-errors';

export default function isRealCPF() {
  return (key, value) => {
    // TODO: buildMessage...
    let message = "não é um CPF válido";

    if (value === undefined) return message;

    let number = value.replace(/[.-]/g, '');
    let sum = 0;
    let rest;

    if (number === "00000000000") return message;

    for (let i=1; i <= 9; i++)
      sum = sum + parseInt(number.substring(i-1, i)) * (11 - i);
    rest = (sum * 10) % 11;

    if ((rest === 10) || (rest === 11)) rest = 0;
    if (rest !== parseInt(number.substring(9, 10)) ) return message;

    sum = 0;
    for (let i = 1; i <= 10; i++) sum = sum + parseInt(number.substring(i-1, i)) * (12 - i);
    rest = (sum * 10) % 11;

    if ((rest === 10) || (sum === 11)) rest = 0;
    if (rest !== parseInt(number.substring(10, 11))) return message;

    return true;
  }
}
