import Ember from 'ember';
import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany, belongsTo } = DS;

export default DS.Model.extend(DependentRelationships, {
  userName: attr('string'),
  email: attr('string'),
  password: attr('string'),
  name: attr('string'),
  notes: attr('string'),
  createdAt: attr('timestamp'),
  updatedAt: attr('timestamp')
});
