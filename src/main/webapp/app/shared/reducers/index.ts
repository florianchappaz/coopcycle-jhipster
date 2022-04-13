import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import cooperative from 'app/entities/cooperative/cooperative.reducer';
// prettier-ignore
import zone from 'app/entities/zone/zone.reducer';
// prettier-ignore
import city from 'app/entities/city/city.reducer';
// prettier-ignore
import deliverMan from 'app/entities/deliver-man/deliver-man.reducer';
// prettier-ignore
import customer from 'app/entities/customer/customer.reducer';
// prettier-ignore
import restaurant from 'app/entities/restaurant/restaurant.reducer';
// prettier-ignore
import meal from 'app/entities/meal/meal.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  cooperative,
  zone,
  city,
  deliverMan,
  customer,
  restaurant,
  meal,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
