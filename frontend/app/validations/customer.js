import {
  validatePresence,
  validateLength,
  validateConfirmation,
  validateFormat
} from 'ember-changeset-validations/validators';
import validateIsRealCPF from '../validations/customs/is_real_cpf';

const { assign } = Ember;

export const CustomerValidations = {
  name: [
    validatePresence(true),
    validateLength({ min: 4 })
  ],
  cpfNumber: [
    validatePresence(true),
    validateFormat({ regex: /[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}/ }),
    validateIsRealCPF(true)
  ],
  email: [
    validateFormat({ type: 'email' })
  ]
};

export default assign({}, CustomerValidations);
