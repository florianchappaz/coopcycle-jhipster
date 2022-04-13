import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './restaurant.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const RestaurantDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const restaurantEntity = useAppSelector(state => state.restaurant.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="restaurantDetailsHeading">
          <Translate contentKey="coopcycleApp.restaurant.detail.title">Restaurant</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{restaurantEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.restaurant.name">Name</Translate>
            </span>
          </dt>
          <dd>{restaurantEntity.name}</dd>
          <dt>
            <span id="adress">
              <Translate contentKey="coopcycleApp.restaurant.adress">Adress</Translate>
            </span>
          </dt>
          <dd>{restaurantEntity.adress}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="coopcycleApp.restaurant.category">Category</Translate>
            </span>
          </dt>
          <dd>{restaurantEntity.category}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.restaurant.city">City</Translate>
          </dt>
          <dd>{restaurantEntity.city ? restaurantEntity.city.id : ''}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.restaurant.cooperative">Cooperative</Translate>
          </dt>
          <dd>{restaurantEntity.cooperative ? restaurantEntity.cooperative.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/restaurant" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/restaurant/${restaurantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RestaurantDetail;
