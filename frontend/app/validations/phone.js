import {
  validateFormat
} from 'ember-changeset-validations/validators';

export default {
  number: [
    validateFormat({ regex: /\([0-9]{2}\) [0-9]{4,5}-[0-9]{4}/ })
  ]
};
