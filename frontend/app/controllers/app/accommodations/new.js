import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
      const flashMessages = Ember.get(this, 'flashMessages');
			let self = this;
      record.save().then(function() {
        flashMessages.success(`Acomodação cadastrada com sucesso em ${record.get('createdAt')}!`);
        self.transitionToRoute('app.accommodations.show', record.id);
      });
		}
	}
});
