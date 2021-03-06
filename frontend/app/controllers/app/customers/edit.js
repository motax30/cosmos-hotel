import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
      const flashMessages = Ember.get(this, 'flashMessages');
			let self = this;
			record.save().then(function() {
        let datetime = new Date().toLocaleString('pt-br');
        flashMessages.success(`Cliente ${record.get('name')} editado com sucesso em ${datetime}!`);
				self.transitionToRoute('app.customers.show', record.id);
			});
		}
	}
});
