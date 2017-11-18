import Ember from 'ember';
import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany, belongsTo } = DS;

export default DS.Model.extend(DependentRelationships, {
  acmTypeInformations: belongsTo('accommodations_Type_Informations', { async: false, cascadeDelete: true }),
  isOcupied: attr('boolean'),
  createdAt: attr('timestamp'),
  updatedAt: attr('timestamp'),
});
