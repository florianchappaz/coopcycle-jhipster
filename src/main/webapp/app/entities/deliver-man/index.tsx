import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DeliverMan from './deliver-man';
import DeliverManDetail from './deliver-man-detail';
import DeliverManUpdate from './deliver-man-update';
import DeliverManDeleteDialog from './deliver-man-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DeliverManUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DeliverManUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DeliverManDetail} />
      <ErrorBoundaryRoute path={match.url} component={DeliverMan} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DeliverManDeleteDialog} />
  </>
);

export default Routes;
