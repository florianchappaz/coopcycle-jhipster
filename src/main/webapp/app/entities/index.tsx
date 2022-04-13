import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cooperative from './cooperative';
import Zone from './zone';
import City from './city';
import DeliverMan from './deliver-man';
import Customer from './customer';
import Restaurant from './restaurant';
import Meal from './meal';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}cooperative`} component={Cooperative} />
      <ErrorBoundaryRoute path={`${match.url}zone`} component={Zone} />
      <ErrorBoundaryRoute path={`${match.url}city`} component={City} />
      <ErrorBoundaryRoute path={`${match.url}deliver-man`} component={DeliverMan} />
      <ErrorBoundaryRoute path={`${match.url}customer`} component={Customer} />
      <ErrorBoundaryRoute path={`${match.url}restaurant`} component={Restaurant} />
      <ErrorBoundaryRoute path={`${match.url}meal`} component={Meal} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
