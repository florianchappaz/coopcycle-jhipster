import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './meal.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MealDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const mealEntity = useAppSelector(state => state.meal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="mealDetailsHeading">
          <Translate contentKey="coopcycleApp.meal.detail.title">Meal</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{mealEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.meal.name">Name</Translate>
            </span>
          </dt>
          <dd>{mealEntity.name}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="coopcycleApp.meal.price">Price</Translate>
            </span>
          </dt>
          <dd>{mealEntity.price}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="coopcycleApp.meal.description">Description</Translate>
            </span>
          </dt>
          <dd>{mealEntity.description}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.meal.restaurant">Restaurant</Translate>
          </dt>
          <dd>{mealEntity.restaurant ? mealEntity.restaurant.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/meal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/meal/${mealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MealDetail;
