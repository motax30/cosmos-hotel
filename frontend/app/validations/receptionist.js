import Ember from 'ember';
import {
  validatePresence,
  validateLength,
  validateFormat
} from 'ember-changeset-validations/validators';
import validateIsRealCPF from '../validations/customs/is_real_cpf';
import validateUniquenessCustomerEmail from "./customs/validate_uniqueness_customer_email";
import and from 'ember-changeset-hofs/utils/and'
import validateUniquenessCustomerCpf from "./customs/validate_uniqueness_customer_cpf";

const { assign } = Ember;

export const ReceptionistValidations = {
  name: and(
    validatePresence(true),
    validateLength({ min: 5 })
  ),
  userName: and(
    validatePresence(true),
    validateLength({ min: 5 })
  ),
  password: and(
    validatePresence(true),
    validateLength({ min: 8 })
  ),
  email: and(
    validatePresence(true),
    validateFormat({ type: 'email' })
  )
};

export default assign({}, ReceptionistValidations);
