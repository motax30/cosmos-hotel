import Ember from 'ember';
import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany, belongsTo } = DS;

export default DS.Model.extend(DependentRelationships, {
  name: attr('string'),
  type: attr('string'),
  dailyPrice: attr('number'),
  numberOfBeds: attr('number'),

  createdAt: attr('timestamp'),
  updatedAt: attr('timestamp'),

  typeSelect: [ { id: 'SINGLE', label: 'Solteiro'}, { id: 'DOUBLE', label: 'Casal'}, { id: 'KING', label: 'King Size'} ],
  typeShow: function() {
    if (this.get('type') !== null)
      return this.get('typeSelect').findBy('id', this.get('type')).label;
    return "Sem registro";
  }.property('type')

});
