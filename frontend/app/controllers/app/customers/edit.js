import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
      const flashMessages = Ember.get(this, 'flashMessages');
			let self = this;
			record.save().then(function() {
        flashMessages.success(`Cliente ${record.get('name')} editado com sucesso em ${record.get('updatedAt')}!`);
				self.transitionToRoute('app.customers.show', record.id);
			});
		}
	}
});
