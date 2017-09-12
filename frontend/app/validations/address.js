import {
  validatePresence,
  validateLength,
  validateFormat
} from 'ember-changeset-validations/validators';

export default {
  zipCode: [
    validateFormat({ regex: /[0-9]{5}-[0-9]{3}/ })
  ],
  street: [
    validatePresence(true),
    validateLength({ min: 4 })
  ],
  number: [
    validatePresence(true)
  ],
  neighborhood: [
    validatePresence(true)
  ],
  city: [
    validatePresence(true)
  ],
  state: [
    validatePresence(true)
  ],
};
