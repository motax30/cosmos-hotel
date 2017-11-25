import Ember from 'ember';
import {
  validatePresence,
  validateLength,
  validateFormat,
  validateNumber
} from 'ember-changeset-validations/validators';
import and from 'ember-changeset-hofs/utils/and'

const { assign } = Ember;

export const AccommodationValidations = {
  name: and(
    validatePresence(true),
    validateLength({ min: 5 })
  ),
  dailyPrice: and(
    validateNumber({ gte: 1 })
  ),
  numberOfBeds: and(
    validateNumber({ integer: true })
  ),
  type: and(
    validatePresence(true),
  )
};

export default assign({}, AccommodationValidations);
