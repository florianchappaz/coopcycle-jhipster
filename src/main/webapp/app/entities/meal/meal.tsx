import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './meal.reducer';
import { IMeal } from 'app/shared/model/meal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Meal = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const mealList = useAppSelector(state => state.meal.entities);
  const loading = useAppSelector(state => state.meal.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="meal-heading" data-cy="MealHeading">
        <Translate contentKey="coopcycleApp.meal.home.title">Meals</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="coopcycleApp.meal.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="coopcycleApp.meal.home.createLabel">Create new Meal</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {mealList && mealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="coopcycleApp.meal.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.meal.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.meal.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.meal.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="coopcycleApp.meal.restaurant">Restaurant</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mealList.map((meal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${meal.id}`} color="link" size="sm">
                      {meal.id}
                    </Button>
                  </td>
                  <td>{meal.name}</td>
                  <td>{meal.price}</td>
                  <td>{meal.description}</td>
                  <td>{meal.restaurant ? <Link to={`restaurant/${meal.restaurant.id}`}>{meal.restaurant.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${meal.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${meal.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${meal.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="coopcycleApp.meal.home.notFound">No Meals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Meal;
