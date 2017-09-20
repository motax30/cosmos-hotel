import Ember from 'ember';
import {
  validatePresence,
  validateLength,
  validateFormat
} from 'ember-changeset-validations/validators';
import validateIsRealCPF from '../validations/customs/is_real_cpf';
import validateUniquenessCustomerEmail from "./customs/validate_uniqueness_customer_email";
import and from 'ember-changeset-hofs/utils/and'

const { assign } = Ember;

export const CustomerValidations = {
  name: and(
    validatePresence(true),
    validateLength({ min: 5 })
  ),
  cpfNumber: and(
    validatePresence(true),
    validateFormat({ regex: /[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}/ }),
    validateIsRealCPF(true)
  ),
  email: and(
    validatePresence(true),
    validateFormat({ type: 'email' }),
    validateUniquenessCustomerEmail()
  )
};

export default assign({}, CustomerValidations);
